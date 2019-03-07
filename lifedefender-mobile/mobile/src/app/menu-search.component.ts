import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';
import { Observable }        from 'rxjs/Observable';
import { Subject }           from 'rxjs/Subject';
// Observable class extensions
import 'rxjs/add/observable/of';
// Observable operators
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import { MenuSearchService } from './menu-search.service';
import { Menu } from './menu';
@Component({
    selector: 'menu-search',
    templateUrl: './menu-search.component.html',
    styleUrls: [ './menu-search.component.css' ],
    providers: [MenuSearchService]
})
export class MenuSearchComponent implements OnInit {
    menus: Observable<Menu[]>;
    private searchTerms = new Subject<string>();
    constructor(
        private menuSearchService: MenuSearchService,
        private router: Router) {}
    // Push a search term into the observable stream.
    search(term: string): void {
        this.searchTerms.next(term);
    }
    ngOnInit(): void {
        this.menus = this.searchTerms
            .debounceTime(300)        // wait 300ms after each keystroke before considering the term
            .distinctUntilChanged()   // ignore if next search term is same as previous
            .switchMap(term => term   // switch to new observable each time the term changes
                // return the http search observable
                ? this.menuSearchService.search(term)
                // or the observable of empty menus if there was no search term
                : Observable.of<Menu[]>([]))
            .catch(error => {
                // TODO: add real error handling
                console.log(error);
                return Observable.of<Menu[]>([]);
            });
    }
    gotoDetail(menu: Menu): void {
        let link = ['/detail', menu.id];
        this.router.navigate(link);
    }
}
