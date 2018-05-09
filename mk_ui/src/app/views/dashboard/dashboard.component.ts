import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {
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