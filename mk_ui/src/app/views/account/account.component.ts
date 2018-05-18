import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountProvider } from '../../providers/account';
import { Observable, Subject } from 'rxjs';

@Component({
    selector: 'app-account',
    templateUrl: './account.component.html',
    styleUrls: ['./account.component.css']
})

export class AccountComponent implements OnInit {
    public account = [];
    public other = []; // list chua cac account khong phai save account
    public saveAccount = []; // nguoc lai
    public lstTmp = [];
    public keyword: string = '';
    public vm: any = {};
    public searchText = '';

    constructor(private pro: AccountProvider,
        private rou: Router) { }

    ngOnInit() {
        this.search();
    }

    public changeType(info: string) {
        if (info === 'other') {
            this.lstTmp = this.account.filter(a => a.type != 'ACC005');
        } else {
            this.lstTmp = this.account.filter(a => a.type === 'ACC005');
        }
    }

    private search() {
        let info = { keyword: '' };
        this.pro.search(info).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.account = rsp.result.data;
                this.lstTmp = this.account.filter(a => a.type != 'ACC005');
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }
}