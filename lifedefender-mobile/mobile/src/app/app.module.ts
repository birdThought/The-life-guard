import {NgModule} from "@angular/core";
import {HashLocationStrategy,LocationStrategy} from '@angular/common';
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms"; // <-- NgModel lives here
import {HttpModule, JsonpModule} from "@angular/http";
import {AppComponent} from "./app.component";
import {DashboardComponent} from "./dashboard.component";
import {MenusComponent} from "./menus.component";
import {MenuDetailComponent} from "./menu-detail.component";
import {MenuService} from "./menu.service";
import {AppRoutingModule} from "./app-routing.module";
// Imports for loading & configuring the in-memory web api
import {MenuSearchComponent} from "./menu-search.component";
import {HelpDetailComponent} from "./component/help/help-detail.component";
import {HelpColumnComponent} from "./component/help/helpcolumn.component";
import {HelpService} from "./service/help.service";
import {GongyiComponent} from "./component/marketing/gongyi.component";
import {MarketingService} from "./service/marketing.service";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        JsonpModule,
        // InMemoryWebApiModule.forRoot(InMemoryDataService),
        AppRoutingModule
    ],
    declarations: [
        AppComponent,
        MenuDetailComponent,
        MenusComponent,
        DashboardComponent,
        MenuSearchComponent,
        HelpDetailComponent,
        HelpColumnComponent,
        GongyiComponent
    ],//declarations数组包含应用中属于该模块的组件、管道和指令的列表。组件在被其它组件引用之前必须先在一个模块中声明过。
    bootstrap: [AppComponent],
    providers: [MenuService,HelpService,MarketingService]//,{provide: LocationStrategy,useClass: HashLocationStrategy}
})
export class AppModule {
}
