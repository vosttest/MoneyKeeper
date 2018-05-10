import { Component, OnInit } from '@angular/core';
import { SettingProvider } from '../../providers/provider';

@Component({
    selector: 'app-setting',
    templateUrl: './setting.component.html',
    styleUrls: ['./setting.component.css']
})

export class SettingComponent implements OnInit {
    public data = [];

    constructor(private pro: SettingProvider) { }

    ngOnInit() {
        this.search();
    }

    private search() {
        this.pro.search().subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.data = rsp.result.data;
                console.log(this.data);
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }
}