import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params}   from '@angular/router';
import {Location}                 from '@angular/common';

import 'rxjs/add/operator/switchMap';
import {HelpService} from "../../service/help.service";
import {Help} from "../../model/help";

@Component({
    templateUrl: './help-detail.component.html',
    styleUrls:['./help-detail.component.css']
})
export class HelpDetailComponent implements OnInit{
    help: Help;
    ngOnInit(): void {
        this.route.params
            .switchMap((params: Params) => this.helpService.getHelp(+params['id']))
            .subscribe(help => this.help = help);
    }
    constructor(private helpService: HelpService,
                private route: ActivatedRoute,
                private location: Location){};
    goBack(): void {
        this.location.back();
    }
}