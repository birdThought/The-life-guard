import {Component, OnInit} from '@angular/core';

import {MenuService} from './menu.service';
import {Menu} from './menu';

@Component({
    selector: 'my-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls:['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
    menus:Menu[]=[];

    constructor(private menuService:MenuService){}

    ngOnInit(): void {
        this.menuService.getMenus()
            .then(menus=> this.menus = menus.slice(1,5));
    }

}