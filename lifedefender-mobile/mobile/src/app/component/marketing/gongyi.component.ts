/**
 * Created by Administrator on 2017/5/2.
 */
import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {MarketingService} from "../../service/marketing.service";
import {GongyiLesson} from "../../model/gongyi-lesson";

@Component({
    selector: 'life-gongyi',
    templateUrl: './gongyi.component.html',//app/component/help
    styleUrls: ['./gongyi.component.css'],//app/component/help
})
export class GongyiComponent implements OnInit {
    gongyiLessons: GongyiLesson[];

    ngOnInit(): void {
        this.getGongyiLessonResponse();
    }

    constructor(private marketingService: MarketingService, private router: Router) {

    };

    getGongyiLessonResponse(): void {
        this.marketingService.getGongyiLessonResponse().then(x => {
            this.gongyiLessons = x.gongyiLessons;
        });
    };
}