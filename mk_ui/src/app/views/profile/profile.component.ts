import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap';
import { UserProvider } from '../../providers/provider';


@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
    public vm: any = {value: 'MAIL'};
    public loader: boolean;

    @ViewChild('SendActiveModal') public sendActiveModal: ModalDirective;

    constructor(private pro: UserProvider, private rou: Router) { }

    ngOnInit() {
        this.infoUser();
        this.changeSend();
    }

    public infoUser() {
        this.loader = true;
        this.pro.profileUser(this.vm).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.vm = rsp.result.info;
            } else {
            }
            this.loader = false;
        }, err => console.log(err));
    }

    public save() {
        this.loader = true;
        this.pro.updateUser(this.vm).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.rou.navigate(['/dashboard']);
            } else {
            }
            this.loader = false;
        }, err => console.log(err));
    }

    public changeSend() {
        console.log(this.vm.value);
        
        let info = { keyword: this.vm.value};
        this.pro.getActivationCode(info).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.sendActiveModal.hide();
            } else {
            }
        }, err => console.log(err));
    }
}
