import { Component, OnInit } from '@angular/core';
import { UserProvider } from '../../providers/provider';

@Component({
    selector: 'app-sign-up',
    templateUrl: './sign-up.component.html',
    styleUrls: ['./sign-up.component.css']
})

export class SignUpComponent implements OnInit {
    public vm: any = { userName: '', password: '' };
    public loader: boolean = false;
    public message = '';

    constructor(private pro: UserProvider) { }

    ngOnInit() { }

    public signUp() {
        this.loader = true;

        this.pro.signUp(this.vm).subscribe((rsp: any) => {
            console.log(rsp);
            if (rsp.status === 'success') {
                this.pro.saveAuth(rsp.result);
            } else {
                this.message = rsp.message;
            }

            this.loader = false;
        }, err => console.log(err));
    }
}