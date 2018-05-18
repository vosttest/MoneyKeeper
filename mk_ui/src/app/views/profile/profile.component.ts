import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserProvider } from '../../providers/provider';


@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
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
        }, err => console.log(err));
    }

    public save() {
        this.pro.updateUser(this.vm).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.rou.navigate(['/dashboard']);
            } else {
            }
        }, err => console.log(err));
    }

}
