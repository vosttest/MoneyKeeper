import { Component, OnInit, HostListener, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { UserProvider } from '../../providers/provider';
import { ModalDirective } from 'ngx-bootstrap';
import { HTTP } from '../../utilities/utility';

@Component({
    selector: 'app-layout',
    templateUrl: './layout.component.html',
    styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {

    @ViewChild("confirmModal") public confirmModal: ModalDirective;

    constructor(public rou: Router, private pro: UserProvider) { }
    public count: any = 0;
    public cssNav: any;
    public isMenuLeft: boolean = false;
    public currentUser = "";

    ngOnInit() {
        this.view();
        this.cssNav = document.getElementsByClassName("nav-item");
        for (var i = 0; i < this.cssNav.length; i++) {
            this.cssNav[i].removeAttribute("tabindex");
        }

    private view() {
        this.pro.view().subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.currentUser = rsp.result.firstName + " " + rsp.result.lastName;
            } else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }
   

    ngAfterViewChecked() {
        if (this.isMenuLeft) {
            document.getElementById("dashboard").style.backgroundColor = "#1CB09A";
        }
    }

    public showConfirm() {
        this.confirmModal.show();
    }

    public signOut() {
        this.pro.signOut();
    }

    public resize() {
        let css = document.getElementsByClassName("nav-link-text") as HTMLCollectionOf<HTMLElement>;
        if (this.count == 0) {
            this.count++;
            document.getElementById("exampleAccordion").style.width = "4%";
            //document.getElementById("resize").style.setProperty("left", "5%", "important");
            for (var i = 0; i < css.length; i++) {
                css[i].style.display = "none";
            }
            document.getElementById("content-wrapper").style.animationName = "content-left";
            document.getElementById("content-wrapper").style.animationDuration = "1s";
            document.getElementById("content-wrapper").style.animationFillMode = "forwards";
            document.getElementById("rotateIcon").style.transform = "rotate(180deg)";
        }
        else {
            this.count = 0;
            document.getElementById("exampleAccordion").style.width = "16.7%";
            setTimeout(function () {
                //document.getElementById("resize").style.setProperty("left", "16.7%", "important");
                for (var i = 0; i < css.length; i++) {
                    css[i].style.display = "initial";
                }
            }, 1000);
            document.getElementById("content-wrapper").style.animationName = "content-right";
            document.getElementById("content-wrapper").style.animationDuration = "1s";
            document.getElementById("content-wrapper").style.animationFillMode = "forwards";
            document.getElementById("rotateIcon").style.transform = "rotate(0)";
        }
    }

    public active(str: string) {
        for (var i = 0; i < this.cssNav.length; i++) {
            this.cssNav[i].style.backgroundColor = "#343a40";
        }

        document.getElementById(str).style.backgroundColor = "#1CB09A";
        this.rou.navigate(["/" + str]);
    }
}

