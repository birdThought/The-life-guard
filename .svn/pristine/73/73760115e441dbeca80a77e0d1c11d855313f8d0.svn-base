$(function() {
    $('.item-nav').on('click', '.helpCenter', function() {
        var container = $('div.helpCenterContainer');
        var route = $('<h4>帮助中心 <i class="glyphicon glyphicon-menu-right"></i> <a href="#" class="content-current">最新问题</a></h4>');
        helpCenterController.listColumn();
    });
});

var helpCenterController = {
   columns: [],
   listColumn: function() {
       var $this = helpCenterController;
       if ($this.columns.length > 0) {
    	   printColumn($this.columns);
    	   return ;
       }
       $.ajax({
           async: true,
           url: 'informationControl.do?helpCenterColumn',
           method: 'GET',
           success: function(obj) {
               if (obj.success) {
                   $this.columns = obj.attributes.columnList;
               }
           },
           complete: function() {
        	   printColumn($this.columns);
           }
       });
       
       function printColumn(columns) {
    	   var columns = $this.columns;
           var lis = "";
           for (var i = 0; i < columns.length; i++) {
               var column = columns[i];
               var id = column.id;
               var title = column.name;
               var li = '<li><a target="_blank" href="informationControl.do?helpCenterIndex&f=' + id + '">' + title + '</a>';
               lis += li;
           }
           var $helplist = $('ol.item-nav-content ul.helplist');
           $helplist.empty();
           $helplist.append(lis);
       }
   }
}