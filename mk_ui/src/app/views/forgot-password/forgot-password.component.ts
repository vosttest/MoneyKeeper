import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { UserProvider } from '../../providers/provider';

@Component({
    selector: 'app-forgot-password',
    templateUrl: './forgot-password.component.html',
    styleUrls: ['../sign-in/sign-in.component.css', './forgot-password.component.css']
})

export class ForgotPasswordComponent implements OnInit {
    public vm: any = { newPassword: '', confirmPassword: '' };
    public loader: boolean = false;
    public token: string = '';
    public pwdPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$$";
    public message: string = '';

    constructor(private rou: Router,
        private act: ActivatedRoute,
        private pro: UserProvider) { }

    ngOnInit() {
        this.act.queryParams
            .filter(params => params.token)
            .subscribe(params => {
                this.token = params.token;
            });
    }

    public resetPassword(valid: boolean) {
        if(!valid){
            return;
        }
        if (this.vm.newPassword != this.vm.confirmPassword) {
            this.message = 'Password does not match!';
            return;
        }
        let obj = { token: this.token, password: this.vm.newPassword };
        
        this.pro.forgotPassword(obj).subscribe((rsp: any) => {
            if(rsp.status === 'success'){
                this.message = '';
                this.pro.saveAuth(rsp.result);
            }else{
                alert(rsp.message);
            }
        }, err => console.log(err));
    }
}