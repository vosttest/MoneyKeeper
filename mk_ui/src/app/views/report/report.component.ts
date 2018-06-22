import { Component, OnInit } from '@angular/core';
import { AccountProvider, ReportProvider } from '../../providers/provider';
import { HTTP } from '../../utilities/utility';
import { Utils } from '../../utils';

@Component({
    selector: 'app-report',
    templateUrl: './report.component.html',
    styleUrls: ['./report.component.css']
})

export class ReportComponent implements OnInit {
    public getReport = [];
    public account = [];
    public vm: any = { accountId: "", total: "", total2: "" };
    public loader: boolean;
    public isShow: boolean = false;
    public message = "";

    constructor(private proAcc: AccountProvider,
        private proReport: ReportProvider,
        private utl: Utils) { }

    ngOnInit() {
        this.search();
    }

    private search() {
        this.loader = true;

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
        this.loader = true;
        console.log(this.vm.fromDate);
        console.log(this.vm.toDate);
        let obj = {
            accountId: this.vm.accountId,
            fromDate: this.vm.fromDate,
            toDate: this.vm.toDate
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

    public export() {
        this.loader = true;

        if (this.getReport.length == 0) {
            this.loader = false;
            this.message = "Not Data.";

            return;
        }

        let x = this.utl.getName("Report_Expense_Income_");
        let tmp: any[] = [];
        let no = 1;

        this.getReport.forEach(i => {
            let item = {
                "No.": no++,
                "Type": i.type,
                "Categories": i.text,
                "Amount": i.amount.toLocaleString(),
                "Start Date": this.utl.formatDate(i.startDate, "dd-MMM-yyyy")
            };
            tmp.push(item);
        });

        this.utl.exportToExcel(tmp, x);

        this.loader = false;
    }
}