import { Component, OnInit } from '@angular/core';
import { UserProvider } from '../../providers/providers';

@Component({
    selector: 'app-sign-in',
    templateUrl: './sign-in.component.html',
    styleUrls: ['./sign-in.component.css']
})

export class SignInComponent implements OnInit {
    public vm: any = { userName: "", password: "" };
    public loader: boolean = false;
    public message = '';

    constructor(private pro: UserProvider) { }

    ngOnInit() { }

    public login() {
        this.loader = true;

        this.pro.signIn(this.vm).subscribe((rsp: any) => {
            console.log(rsp);
            if (rsp.callstatus === "success") {
                this.pro.saveAuth(rsp.authtoken);
            } else {
                this.message = rsp.message;
            }

            this.loader = false;
        }, err => console.log(err));
    }
}