import { Component, OnInit, ViewChild } from '@angular/core';
import { SettingProvider, AccountProvider, ReportProvider } from '../../providers/provider';
import { ModalDirective } from 'ngx-bootstrap';
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

    public vmLang: any = {};
    public vm: any = { accountId: 0, total: "", total2: "" };

    public loader: boolean = false;
    public isShow: boolean = false;

    public minDate = new Date(2018, 1, 1);
    public maxDate = new Date(2050, 12, 12);
    public bsValue: Date = new Date();

    constructor(private proSetting: SettingProvider,
        private proAccount: AccountProvider,
        private proReport: ReportProvider) { }

    ngOnInit() {
        this.getLang();
        this.search();
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
        this.proSetting.view(Setting.CODE_LANGGUAGE).subscribe((rsp: any) => {
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
            }
            else {
                console.log(rsp.message);
            }

            this.loader = false;
        }, err => console.log(err));
    }
}