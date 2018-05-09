import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-account',
    templateUrl: './account.component.html',
    styleUrls: ['./account.component.css']
})

export class AccountComponent implements OnInit {
    public account: any[] = [
        {
            "name": "Douglas  Pace"
        },
        {
            "name": "Mcleod  Mueller"
        },
        {
            "name": "Day  Meyers"
        },
        {
            "name": "Aguirre  Ellis"
        },
        {
            "name": "Cook  Tyson"
        }
    ];

    constructor() { }

    ngOnInit() { }
}