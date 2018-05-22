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
    public loader: boolean;

    constructor(private pro: AccountProvider,
        private rou: Router) { }

    ngOnInit() {
        this.search();
    }

    public changeType(info: string) {
        if (info === 'cash') {
            this.lstTmp = this.account.filter(a => a.type === 'ACC001');
        } else if (info === 'bankAccount') {
            this.lstTmp = this.account.filter(a => a.type === 'ACC002');
        } else if (info === 'atm') {
            this.lstTmp = this.account.filter(a => a.type === 'ACC003');
        } else if (info === 'deposit') {
            this.lstTmp = this.account.filter(a => a.type === 'ACC004');
        } else if (info === 'saveAccount') {
            this.lstTmp = this.account.filter(a => a.type === 'ACC005');
        } else {
            this.lstTmp = this.account.filter(a => a.type === 'ACC006');
        }
    }

    private search() {
        this.loader = true;
        let info = { keyword: '' };
        this.pro.search(info).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.account = rsp.result.data;
                this.lstTmp = this.account.filter(a => a.type === 'ACC001');
            }
            else {
                console.log(rsp.message);
            }
            this.loader = false;
        }, err => console.log(err));
    }
}