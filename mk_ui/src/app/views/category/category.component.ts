import { Component, OnInit } from '@angular/core';
import { UserProvider } from '../../providers/providers';
import { AuthService, FacebookLoginProvider, GoogleLoginProvider } from 'angular5-social-login';

@Component({
    selector: 'app-category',
    templateUrl: './category.component.html',
    styleUrls: ['./category.component.css']
})

export class CategoryComponent implements OnInit {
    constructor() { }

    ngOnInit() { }
}