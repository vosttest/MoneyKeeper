import { Component, OnInit } from '@angular/core';
import { CommonProvider } from '../../providers/provider';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';

@Component({
    selector: 'app-account-add',
    templateUrl: './account-add.component.html',
    styleUrls: ['./account-add.component.css']
})

export class AccountAddComponent implements OnInit {
    public lstType: any[] = [];
    public lstCurrency: any[] = [];
    public lstTerm: any[] = [];
    public lstInterestPaid: any[] = [];
    public lstTermEnd: any[] = [];
    public selected = '';

    constructor(private pro: CommonProvider) { }

    ngOnInit() {
        this.getType('Account');
        this.getType('Currency');
        this.getType('Term');
        this.getType('InterestPaid');
        this.getType('TermEnd');
    }

    private getType(type: string) {
        this.pro.search(type).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                if (type == 'Account') {
                    this.lstType = rsp.result.data;
                    console.log(this.lstType);
                } else if (type == 'Currency') {
                    this.lstCurrency = rsp.result.data;
                    console.log(this.lstCurrency);
                } else if (type == 'Term') {
                    this.lstTerm = rsp.result.data;
                    console.log(this.lstTerm);
                } else if (type == 'InterestPaid') {
                    this.lstInterestPaid = rsp.result.data;
                    console.log(this.lstInterestPaid);
                } else if (type == 'TermEnd') {
                    this.lstTermEnd = rsp.result.data;
                    console.log(this.lstTermEnd);
                }
            }
        }, err => console.log(err));
    }
}