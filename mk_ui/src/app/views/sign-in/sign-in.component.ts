import { Component, OnInit } from '@angular/core';
import { UserProvider } from '../../providers/provider';

@Component({
    selector: 'app-sign-in',
    templateUrl: './sign-in.component.html',
    styleUrls: ['./sign-in.component.css']
})

export class SignInComponent implements OnInit {
    public vm: any = { userName: '', password: '' };
    public loader: boolean = false;
    public message = '';

    constructor(private pro: UserProvider) { }

    ngOnInit() { }

    public signIn() {
        this.loader = true;

        this.pro.signIn(this.vm).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.message = '';
                this.pro.saveAuth(rsp.result);
            } else {
                this.message = rsp.message;
            }

            this.loader = false;
        }, err => console.log(err));
    }
}