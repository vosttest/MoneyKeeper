import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonProvider } from '../../providers/providers';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';

@Component({
    selector: 'app-manage-account-add',
    templateUrl: './manage-account-add.component.html',
    styleUrls: ['./manage-account-add.component.css']
})
export class ManageAccountAddComponent implements OnInit {
    public lstType: any[] = [];

    constructor(private proCommon: CommonProvider) { }

    ngOnInit() {
        this.getType();
    }

    private getType() {
        this.proCommon.search('Account').subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.lstType = rsp.result.data;
                console.log(this.lstType);
            }

        }, err => this.proCommon.handleError(err));
    }
}