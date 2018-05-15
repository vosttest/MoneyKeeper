import { Component, OnInit, ViewChild } from '@angular/core';
import { UserProvider } from '../../providers/provider';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap';

@Component({
    selector: 'app-change-password',
    templateUrl: './change-password.component.html',
    styleUrls: ['./change-password.component.css']
})

export class ChangePasswordComponent implements OnInit {
    public vm: any = { newPassword: '', confirmPassword: '', oldPassword: '' };

    @ViewChild('confirmModal') public confirmModal: ModalDirective;

    constructor(private pro: UserProvider, private rou: Router) { }

    ngOnInit() { }

    public changePassword() {
        if (this.vm.newPassword == this.vm.confirmPassword) {
            this.pro.changePassword(this.vm).subscribe((rsp: any) => {
                if (rsp.status === 'success') {
                    this.rou.navigate(['/sign-in']);
                    alert("Password Update Success");
                } else {
                    alert("Old Password Does Not Match");
                }
            })
        } else {
            alert("New Password And Confirm Password Does Not Match");
        }
    }

    public showConfirm(valid: boolean) {
        if (!valid) {
            return;
        }
        this.confirmModal.show();
    }
}