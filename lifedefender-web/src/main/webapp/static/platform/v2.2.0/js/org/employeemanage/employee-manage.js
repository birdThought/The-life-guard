/**
 * @Author wenxian.cai
 * @Date 2017/6/5 19:36
 */ 

var employee = {};

/**初始化*/
employee.init = function () {



}

/**vm实例*/
employee.vm = new Vue({
    el: '.vue-employee',
    data: {
        result: null,
        employee: [],
        tempEmployee: [], //员工信息预留数据
        currentEmployee: null,
        search: null,
        employeeStatus: null
    },
    methods: {
        /**添加员工*/
        addEmployee: function () {
            window.location.href = '/org/employee/addemployee';
        },
        /**改变工作状态*/
        changeStatus: function () {
            var data = {
                orgUserId: this.currentEmployee.id,
                status: this.employeeStatus
            }
            $.ajax({
                async : true,
                cache : false,
                type: 'POST',
                url: '/org/employee?updateemployee',
                data: data,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    layer.msg(result.msg);
                    if(result.success){
                        employee.vm.currentEmployee.status = this.employeeStatus
                    }
                }
            });
        }
    },
    computed: {
        employee: function () {
            if (this.result != null) {
                return this.result.data;
            }
        },

    },
    watch: {
        employee: function () {
            this.$nextTick(function () {
                //切换员工
                $('.member-content-lists li').on('click', function () {
                    $(this).addClass("on").siblings().removeClass('on');
                    employee.vm.currentEmployee = employee.vm.employee[$(this).index()];
                    employee.vm.employeeStatus = employee.vm.employee[$(this).index()].status
                })
                $('.member-content-lists li:first').click();
                common.addBorder();
            })
        },
        /**监听搜索框*/
        search: function () {
            for (var m in this.employee) {
                if (this.employee[m].realName.indexOf(this.search)<0) {
                    this.employee.splice(m, 1);
                }
            }
            if (this.search.length == 0) {
                this.employee.splice(0, this.employee.length);
                for(var i in this.tempEmployee) {
                    this.employee.push(this.tempEmployee[i]);
                }
            }
        },
        employeeStatus: function () {
            if (this.employeeStatus == this.currentEmployee.status) {
                return;
            }
            layer.confirm('确定修改该员工的工作状态？', function(){
                employee.vm.changeStatus();
            },
            function () {
                if (employee.vm.employeeStatus != employee.vm.currentEmployee.status) {
                    employee.vm.employeeStatus = employee.vm.currentEmployee.status;
                }
            });
        }

    },
    filters: {
        date: function (value, format) {
            return new Date(value).Format(format);
        },
        employeeStatus: function (value) {
            /*正常_0,停用_1,注销_2,离职_4*/
            var msg = null;
            switch (value) {
                case 0:
                    msg = '正常';
                    break;
                case 1:
                    msg = '停用';
                    break;
                case 2:
                    msg = '注销';
                    break;
                case 4:
                    msg = '离职';
                    break;
            }
            return msg;
        },
        userType: function (value) {
            /*管理员_0,服务师_1,都有_2*/
            var msg = null;
            switch (value) {
                case '0':
                    msg = '管理员';
                    break;
                case '1':
                    msg = '服务师';
                    break;
                case '2':
                    msg = '管理员&服务师';
                    break;
            }
            return msg;
        }
    }
});