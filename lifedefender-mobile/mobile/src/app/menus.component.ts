import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

import {Menu} from './menu';
import {MenuService} from './menu.service';

@Component({
    selector: 'my-menus',
    templateUrl: './menus.component.html',
    styleUrls: [ './menus.component.css' ]
})
export class MenusComponent implements OnInit{
    menus : Menu[];
    selectedMenu: Menu;

    title = '生命守护';
    menu: Menu = {
        id: 1,
        name: "帮助中心"
    };

    constructor(private menuService :MenuService,private router:Router){

    };

    onSelect(menu: Menu): void {
        this.selectedMenu = menu;
    };
    getMenus(): void {
        this.menuService.getMenus().then(menus=> this.menus =menus);
    };
    ngOnInit(): void {
        this.getMenus();
    };
    gotoDetail(): void {
        this.router.navigate(['/detail', this.selectedMenu.id]);
    };
    add(name: string): void {
        name = name.trim();
        if (!name) { return; }
        this.menuService.create(name)
            .then(menu => {
                this.menus.push(menu);
                this.selectedMenu = null;
            });
    };
    delete(menu: Menu): void {
        this.menuService
            .delete(menu.id)
            .then(() => {
                this.menus = this.menus.filter(h => h !== menu);
                if (this.selectedMenu === menu) { this.selectedMenu = null; }
            });
    };
}
