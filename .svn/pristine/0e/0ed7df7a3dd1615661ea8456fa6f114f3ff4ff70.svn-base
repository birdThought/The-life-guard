import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import {DashboardComponent} from "./dashboard.component";
import {MenusComponent}  from './menus.component';
import {MenuDetailComponent} from './menu-detail.component';
import {HelpColumnComponent} from "./component/help/helpcolumn.component";
import {HelpDetailComponent} from "./component/help/help-detail.component";
import {GongyiComponent} from "./component/marketing/gongyi.component";

const routes: Routes = [
    { path: '', redirectTo: '/help', pathMatch: 'full' },
    { path: 'dashboard',  component: DashboardComponent },
    { path: 'detail/:id', component: MenuDetailComponent },
    { path: 'help/:id', component: HelpDetailComponent },
    { path: 'help', component: HelpColumnComponent },
    { path: 'menus',     component: MenusComponent },
    { path: 'gongyi',     component: GongyiComponent }
];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})

export class AppRoutingModule {}