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
    public getReport = [];
    public multiSelect = [];
    public vm: any = { total: '', total2: '' };
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
        this.loader = false;

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
        this.loader = true;

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
            
            this.loader = false;
        }, err => console.log(err));
    }
}