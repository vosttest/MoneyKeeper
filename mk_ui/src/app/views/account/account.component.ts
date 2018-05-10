import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-account',
    templateUrl: './account.component.html',
    styleUrls: ['./account.component.css']
})

export class AccountComponent implements OnInit {
    public account: any[] = [
        {
            "name": "Cash"
        },
        {
            "name": "Bank Account"
        },
        {
            "name": "ATM"
        },
        {
            "name": "Deposit Account"
        },
        {
            "name": "Save Account"
        }
    ];

    constructor() { }

    ngOnInit() { }
}