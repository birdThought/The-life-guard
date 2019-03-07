import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params}   from '@angular/router';
import {Location}                 from '@angular/common';

import 'rxjs/add/operator/switchMap';

import {Menu} from './menu';
import {MenuService} from './menu.service';

@Component({
    selector: 'menu-detail',
    templateUrl: './menu-detail.component.html',
    styleUrls:['./menu-detail.component.css']
})
export class MenuDetailComponent implements OnInit{
    ngOnInit(): void {
        this.route.params
        .switchMap((params: Params) => this.menuService.getMenu(+params['id']))
        .subscribe(menu => this.menu = menu);

    }
    @Input() menu: Menu;

    constructor(private menuService: MenuService,
                private route: ActivatedRoute,
                private location: Location){};
    goBack(): void {
        this.location.back();
    }
    save(): void {
        this.menuService.update(this.menu)
            .then(() => this.goBack());
    }
}