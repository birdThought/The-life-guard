/**
 * Created by Administrator on 2017/5/2.
 */
import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {JSONP_PROVIDERS}  from 'angular2/http';
import {HelpService} from "../../service/help.service";
import {HelpColumn} from "../../model/help-column";
import {Observable} from "rxjs";
import {Help} from "../../model/help";
import {HelpResponse} from "../../model/help-response";

@Component({
    selector: 'life-helps',
    templateUrl: './helpcolumn.component.html',//app/component/help
    styleUrls: ['./helpcolumn.component.css'],//app/component/help
})
export class HelpColumnComponent implements OnInit {
    helpColumns: HelpColumn[];
    helps: Help[];
    helpResponse: HelpResponse;
    selectedHelpColumn: HelpColumn;

    ngOnInit(): void {
        this.getHelpResponse();
    }

    constructor(private helpService: HelpService, private router: Router) {

    };

    onSelect(helpColumn: HelpColumn): void {
        if(this.selectedHelpColumn  === helpColumn){
            this.selectedHelpColumn =null;
            return;
        }
        this.selectedHelpColumn = helpColumn;
    };

    getHelpColumns(): void {
        // this.helpColumns= this.helpResponse.helpColumns;
        for (var v in this.helpColumns) // for acts as a foreach
        {
            this.helpColumns[v].helps = this.getHelps(this.helpColumns[v].id)
        }
    };

    getHelpResponse(): void {
        this.helpService.getHelpResponse().then(x => {
            this.helps = x.helps;
            this.helpColumns = x.helpColumns;
            this.getHelpColumns();
            console.log(this.helps)
        });
    };

    getHelps(id: number): Help[] {
        var _helps: Help[] = [];
        for (var v in this.helps) // for acts as a foreach
        {
            if (this.helps[v].columnId === id) {
                _helps.push(this.helps[v]);
            }
        }
        return _helps;
    }
}