import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import {Menu} from './menu';

@Injectable()
export class MenuService {
    private menusUrl = 'api/menus';
    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http:Http){};

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    };
    // URL to web api

    getMenus(): Promise<Menu[]> {
        return this.http.get(this.menusUrl)
            .toPromise()
            .then(response => response.json().data as Menu[])
            .catch(this.handleError);
    };
    getMenusSlowly():Promise<Menu[]>{
        return new Promise(resolve=>{
            setTimeout(()=>resolve(this.getMenus()),2000)
        })
    };

    getMenu(id : number) {
        const url = `${this.menusUrl}/${id}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json().data as Menu)
            .catch(this.handleError);
    }

    update(menu: Menu):Promise<Menu> {
        const url = `${this.menusUrl}/${menu.id}`;
        return this.http.put(url,JSON.stringify(menu),{headers:this.headers})
            .toPromise()
            .then(()=> menu)
            .catch(this.handleError);
    }

    create(name: string): Promise<Menu> {
        return this.http
            .post(this.menusUrl, JSON.stringify({name: name}), {headers: this.headers})
            .toPromise()
            .then(res => res.json().data as Menu)
            .catch(this.handleError);
    }
    delete(id: number): Promise<void> {
        const url = `${this.menusUrl}/${id}`;
        return this.http.delete(url, {headers: this.headers})
            .toPromise()
            .then(() => null)
            .catch(this.handleError);
    }
}