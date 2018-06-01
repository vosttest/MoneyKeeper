import { Component, OnInit, ViewChild } from '@angular/core';
import { AccountProvider, ReportProvider } from '../../providers/provider';
import { ModalDirective } from 'ngx-bootstrap';
import { HTTP } from '../../utilities/utility';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {
    public account = [];
    public getReport = [];

    public vm: any = { accountId: "", total: "", total2: "" };

    public loader: boolean = false;
    public isShow: boolean = false;

    public minDate = new Date(2018, 1, 1);
    public maxDate = new Date(2050, 12, 12);
    public bsValue: Date = new Date();

    constructor(private proAcc: AccountProvider,
        private proReport: ReportProvider) { }

    ngOnInit() {
        this.search();
    }

    private search() {
        this.loader = false;

        this.proAcc.search("", true).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.account = rsp.result.data;
            }
            else {
                console.log(rsp.message);
            }

            this.loader = false;
        }, err => console.log(err));
    }

    public report() {
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
        }, err => console.log(err));
    }
}