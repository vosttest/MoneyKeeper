import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserProvider } from '../../providers/provider';

@Component({
    selector: 'app-layout',
    templateUrl: './layout.component.html',
    styleUrls: ['./layout.component.css']
})
export class LayoutComponent {
    constructor(public rou: Router, private pro: UserProvider) { }

    public signOut() {
        let answer = confirm("You want to sign out?");
        if (answer) {
            this.pro.signOut();
        }
    }
}