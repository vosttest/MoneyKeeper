import { Component, OnInit } from '@angular/core';
import { AccountProvider, ReportProvider } from '../../providers/provider';

@Component({
    selector: 'app-report',
    templateUrl: './report.component.html',
    styleUrls: ['./report.component.css']
})

export class ReportComponent implements OnInit {
    public getReport = [];
    public multiSelect = [];
    public vm: any = { total: '', total2: '' };
    public options: any = [];
    public loader: boolean;
    public isShow: boolean = false;

    config = {
        displayKey: "text",
        search: true,
    };

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
        this.loader = true;
        let info = { keyword: '' };
        this.proAcc.search(info).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
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
        let obj = {
            accountId: this.multiSelect[0].id,
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