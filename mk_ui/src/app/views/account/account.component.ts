import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountProvider, CommonProvider, SettingProvider } from '../../providers/provider';
import { HTTP, Setting } from '../../utilities/utility';

@Component({
    selector: 'app-account',
    templateUrl: './account.component.html',
    styleUrls: ['./account.component.css']
})

export class AccountComponent implements OnInit {
    public account = [];
    public lstTmp = [];
    public lstType = [];
    public accountType = [];

    public keyword: string = "";
    public vm: any = { type: "" };
    public searchText = "";
    public currency = "";
    public loader = false;

    constructor(private proSetting: SettingProvider,
        private pro: AccountProvider,
        private proCom: CommonProvider,
        private rou: Router) { }

    ngOnInit() {
        this.search();
        this.searchCurrency();
    }

    private search() {
        this.loader = true;

        this.pro.search("").subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.account = rsp.result.data;

                let t = this.account.map(a => a.code).filter((value, index, i) => i.indexOf(value) === index);
                t.sort();

                let t2 = [];
                for (let i = 0; i < t.length; i++) {
                    t2.push(this.account.filter(a => a.code == t[i]));

                    let total = t2[i].reduce((sum, item) => sum + item.balance * item.rate, 0);
                    let count = t2[i].length;
                    let t1 = { code: t[i], total: total, count: count };

                    this.accountType.push(t1);
                }
            }
            else {
                console.log(rsp.message);
            }

            this.loader = false;
        }, err => console.log(err));
    }

    private searchCurrency() {
        this.proSetting.search().subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                let data = rsp.result.data;
                data.forEach(element => {
                    if (element.code === Setting.CODE_CURRENCY) {
                        this.currency = element.value === "" || element.value === null ? "VND" : element.value;
                    }
                });
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }
}