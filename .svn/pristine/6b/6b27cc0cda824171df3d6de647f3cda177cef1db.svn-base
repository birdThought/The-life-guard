import { Injectable } from '@angular/core';
import { Http }       from '@angular/http';
import { Observable }     from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Menu }           from './menu';
@Injectable()
export class MenuSearchService {
    constructor(private http: Http) {}
    search(term: string): Observable<Menu[]> {
        return this.http
            .get(`app/menus/?name=${term}`)
            .map(response => response.json().data as Menu[]);
    }
}
