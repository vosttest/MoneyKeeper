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
    public searchText = '';
    public loader: boolean = false;
    public reportExpense = [];
    public reportIncome = [];
    public multiSelect = [];
    public vm: any = {};
    public options: any = [];
    public isShow: boolean = false;

    config = {
        displayKey: "text",
        search: true,
    };

    // Datepicker

    minDate = new Date(2018, 1, 1);
    maxDate = new Date(2050, 12, 12);

    bsValue: Date = new Date();

    @ViewChild('messageModal') public messageModal: ModalDirective;

    constructor(private proAcc: AccountProvider,
        private proReport: ReportProvider) { }

    ngOnInit() {
        this.search();
    }

    private search() {
        this.loader = true;
        let info = { keyword: '' };
        this.proAcc.search(info).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.account = rsp.result.data;

                let name = [];
                rsp.result.data.forEach(obj => {
                    let a = { "id": obj.id, "text": obj.text };
                    name.push(a);
                });

                this.options = name;
            }
            else {
                console.log(rsp.message);
            }
            this.loader = false;
        }, err => console.log(err));
    }

    public report() {
        let t = [];
        this.multiSelect.forEach(obj => {
            t.push(obj.id);
        });

        let obj = {
            accountId: t,
            fromDate: this.vm.fromDate,
            toDate: this.vm.toDate
        };

        this.proReport.report(obj).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.isShow = true;
                this.reportExpense = rsp.result.data;
                this.reportIncome = rsp.result.data2;
            }
            else {
                this.messageModal.show();
            }
        }, err => console.log(err));
    }
}