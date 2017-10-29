package com.yufeimen.application.service.impl;

import org.springframework.stereotype.Service;

@Service
public class FileService {

//    @Autowired
//    ReportMapper reportMapper;
//
//    public LinkedList<XLSModel> upload(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException, IllegalAccessException {
//        LinkedList<XLSModel> files = new LinkedList<XLSModel>();
////        Report report = null;
//
//        Iterator<String> itr = request.getFileNames();
//        MultipartFile mpf = null;
//        while (itr.hasNext()) {
//            XLSModel model = null;
//            mpf = request.getFile(itr.next());
//            String fileType = mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf(".") + 1);
//
//            if (fileType.equalsIgnoreCase("xlsx")) {
//                model = new XLSXUtil().getFromStream(mpf.getInputStream());
//            } else if (fileType.equalsIgnoreCase("xls")) {
//                model = new XLSUtil().getFromStream(mpf.getInputStream());
//            } else {
//                throw new RuntimeException("文件类型错误");
//            }
//
//            for (int i = 1; i < model.getContent().size(); i++) {
//                Report report = new Report();
//
//                String[] data = new String[model.getContent().get(i).size()];
//                for (int j = 0; j < model.getContent().get(i).size(); j++) {
//                    data[j] = (String) model.getContent().get(i).get(j);
//                }
//                Report newReport = new ObjectUtil<Report>().initData(report, data, 6);
//                reportMapper.insert(newReport);
//            }
//
//            files.add(model);
//
//        }
//
//        return files;
//    }


}
