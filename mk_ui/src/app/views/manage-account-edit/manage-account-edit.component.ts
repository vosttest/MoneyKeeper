import { Component, OnInit } from '@angular/core';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { Router } from '@angular/router';
import { CommonProvider } from '../../providers/providers';

@Component({
    selector: 'app-manage-account-edit',
    templateUrl: './manage-account-edit.component.html',
    styleUrls: ['./manage-account-edit.component.css']
})

export class ManageAccountEditComponent implements OnInit {
    public lstType: any[] = [];
    public lstCurrency: any[] = [];
    public lstTerm: any[] = [];
    public lstInterestPaid: any[] = [];
    public lstTermEnd: any[] = [];
    public selected = '';

    constructor(private proCommon: CommonProvider) { }

    ngOnInit() {
        this.getType('Account');
        this.getType('Currency');
        this.getType('Term');
        this.getType('InterestPaid');
        this.getType('TermEnd');
    }

    private getType(type: string) {
        this.proCommon.search(type).subscribe((rsp: any) => {
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

        }, err => this.proCommon.handleError(err));
    }
}