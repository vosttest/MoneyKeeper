import { Component, OnInit } from '@angular/core';
import { UserProvider } from '../../providers/provider';

@Component({
    selector: 'app-change-password',
    templateUrl: './change-password.component.html',
    styleUrls: ['./change-password.component.css']
})

export class ChangePasswordComponent implements OnInit {
    public vm: any = { newPassword: '', confirmPassword: '', oldPassword: '' };
    constructor(private pro: UserProvider) { }

    ngOnInit() { }

    public changePassword() {
        if (this.vm.newPassword == this.vm.confirmPassword) {
            this.pro.changePassword(this.vm).subscribe((rsp: any) => {
                if (rsp.status === 'success') {
                    console.log(this.vm.oldPassword);
                    this.pro.saveAuth(rsp.result);
                } else {

                }
            })

        }


    }
}


