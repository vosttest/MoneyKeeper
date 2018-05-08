import { Component, OnInit } from '@angular/core';
import { UserProvider } from '../../providers/providers';
import { AuthService, FacebookLoginProvider, GoogleLoginProvider } from 'angular5-social-login';

@Component({
    selector: 'app-sign-in',
    templateUrl: './sign-in.component.html',
    styleUrls: ['./sign-in.component.css']
})

export class SignInComponent implements OnInit {
    public vm: any = { userName: '', password: '' };
    public loader: boolean = false;
    public message = '';

    constructor(private pro: UserProvider, private svr: AuthService) { }

    ngOnInit() { }

    public login() {
        this.loader = true;

        this.pro.signIn(this.vm).subscribe((rsp: any) => {
            console.log(rsp);
            if (rsp.callstatus === 'success') {
                this.pro.saveAuth(rsp.authtoken);
            } else {
                this.message = rsp.message;
            }

            this.loader = false;
        }, err => console.log(err));
    }

    public signIn(socialPlatform: string) {
        let socialPlatformProvider;
        if (socialPlatform == 'facebook') {
            socialPlatformProvider = FacebookLoginProvider.PROVIDER_ID;
        } else if (socialPlatform == 'google') {
            socialPlatformProvider = GoogleLoginProvider.PROVIDER_ID;
        }
        this.svr.signIn(socialPlatformProvider).then(
            data => {
                let vm = {
                    'accountNo': '',
                    'contactNo': '',
                    'email': data.email,
                    'firstName': '',
                    'lastName': '',
                    'password': data.id,
                    'remarks': '',
                    'userName': data.id
                };
                this.pro.signUp(data).subscribe(res => {
                    console.log(res);
                });
            }
        );
    }
}