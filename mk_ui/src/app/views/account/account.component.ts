import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountProvider, CommonProvider } from '../../providers/provider';
import { Observable, Subject } from 'rxjs';

@Component({
    selector: 'app-account',
    templateUrl: './account.component.html',
    styleUrls: ['./account.component.css']
})

export class AccountComponent implements OnInit {
    public data = [];
    public lstTmp = [];
    public lstType = [];
    public keyword: string = '';
    public vm: any = { type: 'showAll' };
    public searchText = '';
    public loader: boolean;

    constructor(private pro: AccountProvider,
        private proCom: CommonProvider,
        private rou: Router) { }

    ngOnInit() {
        this.searchByType('Account');
        this.search();
    }

    private search() {
        this.loader = true;

        let info = { keyword: '' };
        this.pro.search(info).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.data = rsp.result.data;
                this.lstTmp = this.data;
            }
            else {
                console.log(rsp.message);
            }

            this.loader = false;
        }, err => console.log(err));
    }

    private searchByType(type: string) {
        this.loader = true;

        this.proCom.search(type).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                if (type == 'Account') {
                    this.lstType = rsp.result.data;
                }
            }
            else {
                console.log(rsp.message);
            }

            this.loader = false;
        }, err => console.log(err));
    }

    public changeType(value: string) {
        if (value === 'showAll') {
            this.data = this.lstTmp;
        } else {
            this.data = this.lstTmp.filter(a => a.type === value);
        }
    }
}