import { Injectable } from '@angular/core';
import {Headers, Http, Response, Jsonp, URLSearchParams} from '@angular/http';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import {HelpColumn} from "../model/help-column";
import {Observable} from "rxjs";
import {HelpResponse} from "../model/help-response";
import {Help} from "../model/help";
import {ApiDomain} from "../config/app-config";

@Injectable()
export class HelpService {
    private reqUrl = ApiDomain;
    private headers = new Headers({'Content-Type': 'application/json'});
    private extractData(res:any){
        let body = res.json();
        return body.obj || {}
    };

    constructor(private http:Http,private jsonp: Jsonp){};

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    };
    // URL to web api
    getHelpResponse(): Promise<HelpResponse> {
        return this.http.get( `${this.reqUrl}/helpcolumn`,this.headers).toPromise()
            .then(res => res.json().obj as HelpResponse)
            .catch(this.handleError);
    };
    getHelp(id : number) {
        const url = `${this.reqUrl}/help/${id}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json().obj as Help)
            .catch(this.handleError);
    }

    getHelps(columnId: number) {

    }
}