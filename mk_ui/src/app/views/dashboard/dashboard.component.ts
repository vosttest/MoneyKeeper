import { Component, OnInit, ViewChild } from '@angular/core';
import { AccountProvider, ReportProvider } from '../../providers/provider';
import { ModalDirective } from 'ngx-bootstrap';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {
    public account = [];
    public loader: boolean = false;
    public getReport = [];
    public vm: any = { accountId: '', total: '', total2: '' };
    public isShow: boolean = false;


    // Datepicker

    minDate = new Date(2018, 1, 1);
    maxDate = new Date(2050, 12, 12);

    bsValue: Date = new Date();

    constructor(private proAcc: AccountProvider,
        private proReport: ReportProvider) { }

    ngOnInit() {
        this.search();
    }

    private search() {
        this.loader = false;

        let info = { keyword: '' };
        this.proAcc.search(info).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
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
            accountId: this.vm.accountId,
            fromDate: this.vm.fromDate,
            toDate: this.vm.toDate
        };

        this.proReport.report(obj).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
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