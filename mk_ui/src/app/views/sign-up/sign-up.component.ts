import { Component, OnInit } from '@angular/core';
import { UserProvider } from '../../providers/provider';
import { HTTP } from '../../utilities/utility';

@Component({
    selector: 'app-sign-up',
    templateUrl: './sign-up.component.html',
    styleUrls: ['../sign-in/sign-in.component.css', './sign-up.component.css']
})

export class SignUpComponent implements OnInit {
    public vm: any = { userName: "", password: "" };
    public loader: boolean = false;
    public message = "";
    public pwdPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$$";

    constructor(private pro: UserProvider) { }

    ngOnInit() { }

    public signUp() {
        this.loader = true;

        this.pro.signUp(this.vm).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.message = "";
                this.pro.saveAuth(rsp.result);
            } else {
                this.message = rsp.message;
            }

            this.loader = false;
        }, err => console.log(err));
    }
}