import { Component, OnInit, HostListener } from '@angular/core';
import { Router } from '@angular/router';
import { UserProvider } from '../../providers/provider';

@Component({
    selector: 'app-layout',
    templateUrl: './layout.component.html',
    styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
    constructor(public rou: Router, private pro: UserProvider) { }

    ngOnInit() {
    }

    public signOut() {
        let answer = confirm("You want to sign out?");
        if (answer) {
            this.pro.signOut();
        }
    }
}

