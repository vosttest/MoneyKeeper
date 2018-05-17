import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserProvider } from '../../providers/provider';



@Component({
    selector: 'app-info-user',
    templateUrl: './info-user.component.html',
    styleUrls: ['./info-user.component.css']
})
export class InfoUserComponent implements OnInit {
    public vm: any = {};

    constructor(private pro: UserProvider, private rou: Router) { }

    ngOnInit() {
        this.infoUser();
    }

    public infoUser() {
        this.pro.infoUser(this.vm).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.vm = rsp.result.info;
            } else {
            }
        })
    }

    public save() {
        this.pro.updateUser(this.vm).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.rou.navigate(['/dashboard']);
            } else {
            }
        })
    }
}
