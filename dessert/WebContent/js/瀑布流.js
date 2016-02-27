window.onload = function () {
    imgLocation("container", "box");

    //模拟网络图片流
    var imgData = {"data": [{"src": "1.jpg"}, {"src": "2.jpg"}, {"src": "3.jpg"}, {"src": "4.jpg"}, {"src": "5.jpg"}]};
    //监听浏览器滚动条
    window.onscroll = function () {
        if (checkFlag("container", "box")) {
            var cParent = document.getElementById("container");
            for (var i = 0; i < imgData.data.length; i++) {
                //创建图片节点
                var cContent = document.createElement("div");
                cContent.className = "box";
                cParent.appendChild(cContent);

                var boxImg = document.createElement("div");
                boxImg.className = "box_img";
                cContent.appendChild(boxImg);

                var img = document.createElement("img");
                img.src = "image/desserts/shop1/" + imgData.data[i].src;
                boxImg.appendChild(img);
            }
            imgLocation("container", "box");
        }
    }
}

/**
 * 监听浏览器滚动条滑动到最低端
 * @param parent
 * @param content
 * @return boolean  true 允许加载图片； false 禁止加载图片
 */
function checkFlag(parent, content) {
    var cParent = document.getElementById(parent);
    var cContent = getChildElemet(cParent, content);

    //最后一张图片距离顶部的高度，通过offsetTop得到
    var lastContentHeight = cContent[cContent.length - 1].offsetTop;
    //滚动条高度
    var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
    //当前页面的高度
    var pageHeight = document.documentElement.clientHeight || document.body.clientHeight;

    if (lastContentHeight < scrollTop + pageHeight) {
        return true;
    }
}

/**
 * 第二排的图片，从第一排图片高度最小的地方依次放置
 * @param parent
 * @param content
 */
function imgLocation(parent, content) {
    //将父级空间parent下的所有内容全部取出
    var cParent = document.getElementById(parent);
    var cContent = getChildElemet(cParent, content);
    
    if(cContent.length==0){
        return;
    }
    //每张图片的宽度
    var imgWidth = cContent[0].offsetWidth;
    //每行可以放多少张图片
    // var cols = Math.floor(document.documentElement.clientWidth / imgWidth);
    var cols = Math.floor($("#desserts").width() / imgWidth);
    cParent.style.cssText = "width:" + imgWidth * cols + "px;margin:0 auto";

    //存储第一排图片的高度
    var boxHeightArr = [];
    for (var i = 0; i < cContent.length; i++) {
        if (i < cols) {
            boxHeightArr[i] = cContent[i].offsetHeight;
        } else {
            //得到第一排图片的最小的高度
            var minHeight = Math.min.apply(null, boxHeightArr);
            //得到第一排哪一张图片高度是最小的
            var minIndex = getMinHeightLocation(boxHeightArr, minHeight);

            //摆放图片

            //设置该图片位置布局为绝对位置
            cContent[i].style.position = "absolute";

            //设置其距上高度为第一排图片的最小高度，注意，最后的单位“px”不能忘记
            cContent[i].style.top = minHeight + "px";

            //设置其恰巧在第一排高度最小的图片下面
            // 即，该图片距坐的距离，是第一排高度最小的图片的左边距离，通过offsetLeft来得到，同样不要忘记单位
            cContent[i].style.left = cContent[minIndex].offsetLeft + "px";

            //为了防止所有图片都堆在第一排高度最小的图片下面
            //当第一排高度最小的图片下面放置了一张图片后，将第一排的最小高度值加上刚放置在它下面的图片的高度
            //使得第二排的第二张图片继续寻找第一排新的最小高度
            boxHeightArr[minIndex] = boxHeightArr[minIndex] + cContent[i].offsetHeight;
        }
    }
}

function getMinHeightLocation(boxHeightArr, minHeight) {
    for (var i = 0; i < boxHeightArr.length; i++) {
        if (boxHeightArr[i] == minHeight) {
            return i;
        }
    }
}

function getChildElemet(parent, content) {
    var contentArr = [];
    var allContent = parent.getElementsByTagName("*");
    for (var i = 0; i < allContent.length; i++) {
        if (allContent[i].className == content) {
            contentArr.push(allContent[i]);
        }
    }
    return contentArr;
}