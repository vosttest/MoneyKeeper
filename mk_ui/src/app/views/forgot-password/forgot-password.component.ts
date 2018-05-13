import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-forgot-password',
    templateUrl: './forgot-password.component.html',
    styleUrls: ['./forgot-password.component.css']
})

export class ForgotPasswordComponent implements OnInit {
    public vm: any = { newPassword: "", confirmPassword: "" };
    constructor() { }

    ngOnInit() { }

    public change() {

    }

    public resetPassword(){
        console.log(this.vm);
    }
}