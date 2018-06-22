import { Component, OnInit } from '@angular/core';
import { SettingProvider, AccountProvider, ReportProvider } from '../../providers/provider';
import { HTTP, Setting } from '../../utilities/utility';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {
    public lang = [];
    public account = [];
    public getReport = [];
    public accountType = [];

    public vmLang: any = {};
    public vm: any = { accountId: "", total: "", total2: "" };
    public currency = "";

    public loader: boolean = false;
    public isShow: boolean = false;

    constructor(private proSetting: SettingProvider,
        private proAccount: AccountProvider,
        private proReport: ReportProvider) { }

    ngOnInit() {
        this.getLang();
        this.search();
        this.searchCurrency();
    }

    public report() {
        this.loader = true;

        let obj = {
            "accountId": this.vm.accountId,
            "fromDate": this.vm.fromDate,
            "toDate": this.vm.toDate
        };

        this.proReport.report(obj).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.isShow = true;
                this.getReport = rsp.result.data;
                this.vm.total = rsp.result.total;
                this.vm.total2 = rsp.result.total2;
            }
            else {
                console.log(rsp.message);
            }

            this.loader = false;
        }, err => console.log(err));
    }

    private getLang() {
        this.proSetting.view(Setting.CODE_LANGUAGE).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                let file = "";

                switch (rsp.result.value) {
                    case "vi-vn":
                        file = "language/vi-vn.json"
                        break;

                    default:
                        file = "language/en-us.json"
                        break;
                }

                this.proSetting.getLang(file).subscribe((rsp: any) => {
                    this.lang = rsp.data;
                    let x = this.lang.filter(p => p.page === "dashboard");

                    this.vmLang.hExpenseIncome = x.filter(p => p.code === "H0001")[0].text;
                    this.vmLang.hAccounts = x.filter(p => p.code === "H0002")[0].text;
                    this.vmLang.lAccount = x.filter(p => p.code === "L0004")[0].text;
                    this.vmLang.lViewOn = x.filter(p => p.code === "L0005")[0].text;
                    this.vmLang.bView = x.filter(p => p.code === "B0001")[0].text;
                }, err => console.log(err));
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    private search() {
        this.loader = false;

        this.proAccount.search("", true).subscribe((rsp: any) => {
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