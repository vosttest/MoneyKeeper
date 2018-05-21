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
    public searchText = '';
    public loader: boolean = false;

    constructor(private proAcc: AccountProvider) { }

    ngOnInit() {
        this.search();
    }

    private search() {
        this.loader = true;
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
}