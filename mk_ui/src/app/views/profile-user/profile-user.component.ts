import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserProvider } from '../../providers/provider';


@Component({
    selector: 'app-profile-user',
    templateUrl: './profile-user.component.html',
    styleUrls: ['./profile-user.component.css']
})
export class ProfileUserComponent implements OnInit {
    public vm: any = {};

    constructor(private pro: UserProvider, private rou: Router) { }

    ngOnInit() {
        this.infoUser();
    }
    public infoUser() {
        this.pro.profileUser(this.vm).subscribe((rsp: any) => {
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
