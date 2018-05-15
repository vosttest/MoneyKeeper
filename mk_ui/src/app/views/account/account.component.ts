import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountProvider } from '../../providers/account';

@Component({
    selector: 'app-account',
    templateUrl: './account.component.html',
    styleUrls: ['./account.component.css']
})

export class AccountComponent implements OnInit {
    public account = [];
    public keyword: string = '';
    public vm: any = {};

    constructor(private pro: AccountProvider,
        private rou: Router) { }

    ngOnInit() {
        this.search();
    }

    private search() {
        let info = { keyword: this.keyword };
        this.pro.search(info).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.account = rsp.result.data;
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }
}