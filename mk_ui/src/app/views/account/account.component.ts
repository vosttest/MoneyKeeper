import { Component, OnInit } from '@angular/core';
import { AccountProvider } from '../../providers/account';

@Component({
    selector: 'app-account',
    templateUrl: './account.component.html',
    styleUrls: ['./account.component.css']
})

export class AccountComponent implements OnInit {
    public account = [];
    public keyword: string = '';

    constructor(private pro: AccountProvider) { }

    ngOnInit() {
        this.search();
    }

    private search() {
        let obj = { keyword: this.keyword };
        this.pro.search(obj).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.account = rsp.result.data;

                console.log(this.account);
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }
}