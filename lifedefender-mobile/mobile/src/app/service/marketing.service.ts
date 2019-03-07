import { Injectable } from '@angular/core';
import {Headers, Http, Response, Jsonp, URLSearchParams} from '@angular/http';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import {Observable} from "rxjs";
import {GongyiLesson} from "../model/gongyi-lesson";
import {ApiDomain} from "../config/app-config";
import {GongyiLessonResponse} from "../model/gongyi-lesson-response";

@Injectable()
export class MarketingService {
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
    getGongyiLessonResponse(): Promise<GongyiLessonResponse> {
        return this.http.get( `app/gongyilessons.json`,this.headers).toPromise()
            .then(res => res.json().obj as GongyiLessonResponse)
            .catch(this.handleError);
    };
}