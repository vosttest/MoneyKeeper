import { Component, OnInit } from '@angular/core';
import { AccountProvider } from '../../providers/provider';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {
    public account = [];
    public vm: any = {};

    constructor(private proAcc: AccountProvider) { }

    ngOnInit() {
        this.search();
    }

    private search() {
        let info = { keyword: '' };
        this.proAcc.search(info).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.account = rsp.result.data;
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }
}